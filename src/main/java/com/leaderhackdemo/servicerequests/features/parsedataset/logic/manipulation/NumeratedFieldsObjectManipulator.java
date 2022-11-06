package com.leaderhackdemo.servicerequests.features.parsedataset.logic.manipulation;

import com.leaderhackdemo.servicerequests.features.parsedataset.model.data.NumeratedFieldNamingClass;
import com.leaderhackdemo.servicerequests.features.parsedataset.model.manipulation.DataFieldAccessor;
import com.leaderhackdemo.servicerequests.features.parsedataset.model.manipulation.DataFieldDescriptor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

@Slf4j
public class NumeratedFieldsObjectManipulator {
    private Map<DataFieldDescriptor, DataFieldAccessor> fieldAccessorsMap = new HashMap<>();

    private String delimiter;           // delimiter which is used in class field names
    private int fieldNumberPosition;    // position of the field number in field name string

    private int fieldNamePosition;      // position of the field name in field name string
    private Class<? extends NumeratedFieldNamingClass> typeToManipulate;

    private Supplier<? extends NumeratedFieldNamingClass> constructObjectToManipulate;

    private void initFieldAccessorsMap(){
        if (typeToManipulate != null){
            List<Method> allMethods = List.of(typeToManipulate.getMethods());

            List<Method> fieldGetters = getMethodsBySubstringInName(allMethods, "getField");

            fieldGetters.forEach(
                    getter -> {
                        List<String> fieldNameParts = List.of(getter.getName().split(delimiter));

                        fieldAccessorsMap.put(
                              DataFieldDescriptor.builder()
                                          .fieldNumber(
                                                  Integer.parseInt(
                                                                    fieldNameParts.get(fieldNumberPosition)
                                                  )
                                          )
                                          .name(
                                                  fieldNameParts.get(fieldNamePosition)
                                          )
                                      .build(),
                              DataFieldAccessor.builder()
                                          .fieldType(getter.getReturnType())
                                          .getter(getter)
                                      .build()
                        );
                        log.info("[MANIPULATOR]: getter added "+getter.getName());
                    }
            );

            List<Method> fieldSetters = getMethodsBySubstringInName(allMethods, "setField");

            fieldSetters.forEach(
                    setter -> {
                        List<String> fieldNameParts = List.of(setter.getName().split(delimiter));

                        DataFieldDescriptor descriptorToTouch = DataFieldDescriptor.builder()
                                .fieldNumber(
                                        Integer.parseInt(
                                                fieldNameParts.get(fieldNumberPosition)
                                        )
                                )
                                .name(
                                        fieldNameParts.get(fieldNamePosition)
                                )
                                .build();

                        DataFieldAccessor existingAccessor = fieldAccessorsMap.get(descriptorToTouch);
                        if (existingAccessor != null){
                            existingAccessor.setSetter(setter);
                            log.info("[MANIPULATOR]: setter added "+setter.getName());
                        } else {
                            log.info("[MANIPULATOR]: oops, a problem with "+setter.getName());
                        }



                    }
            );
        }
    }

    @NotNull
    private static List<Method> getMethodsBySubstringInName(List<Method> allMethods, String getField) {
        List<Method> fieldGetters = allMethods
                                                .stream()
                                                .filter(
                                                        method -> method.getName()
                                                                .contains(getField)
                                                )
                                                .toList();
        return fieldGetters;
    }

    @Builder
    private NumeratedFieldsObjectManipulator(
            String delimiter,
            int fieldNumberPosition,
            int fieldNamePosition,
            Class<? extends NumeratedFieldNamingClass> typeToManipulate,
            Supplier<? extends NumeratedFieldNamingClass> constructObjectToManipulate
    ){
        this.delimiter = delimiter == null ? delimiter: "_";
        this.fieldNumberPosition = fieldNumberPosition;
        this.typeToManipulate = typeToManipulate;
        this.fieldNamePosition = fieldNamePosition;
        this.constructObjectToManipulate = constructObjectToManipulate;

        initFieldAccessorsMap();
    }

    String fillObjectFieldErrorMsgTemplate = "[MANIPULATOR]: oops, cannot set field %s%s%s due to error";
    String fillObjectFieldLogMsgTemplate = "[MANIPULATOR]: field %s%s%s set OK";
    public NumeratedFieldNamingClass constructObjectFromList(List<String> objectParts){

        NumeratedFieldNamingClass objectToFill = null;

        if (constructObjectToManipulate != null){
            objectToFill = constructObjectToManipulate.get();
        }

        if (objectToFill != null){
            NumeratedFieldNamingClass finalObjectToFill = objectToFill;

            fieldAccessorsMap.entrySet().forEach(
                    entry -> {
                        DataFieldDescriptor desc = entry.getKey();
                        DataFieldAccessor access = entry.getValue();

                        String fieldValue = objectParts.get(desc.getFieldNumber());

                        if (fieldValue != null){
                            try {
                                access.getSetter().invoke(finalObjectToFill, fieldValue);
//                                log.info(
//                                        String.format(
//                                                fillObjectFieldLogMsgTemplate,
//                                                desc.getFieldNumber(),
//                                                delimiter,
//                                                desc.getName()
//                                        )
//                                );
                            } catch (Exception e) {
                                log.info(
                                        String.format(
                                                fillObjectFieldErrorMsgTemplate,
                                                desc.getFieldNumber(),
                                                delimiter,
                                                desc.getName(),
                                                e.getMessage()
                                        )
                                );
                            }
                        }
                    }
            );
        }

        return objectToFill;
    }

    private String logErrorMsgTemplate = "[EVENT FIELD MANAGER]: cannot get value of field №%s due to error %s";

    public Object getValueByFieldNumber(Object source, int fieldNumber){
        Object result = null;

        DataFieldAccessor fieldAccessor = getDataFieldAccessorByFieldNumber(fieldNumber);

        if (fieldAccessor != null){
            try{
                result = invokeGetter(source, fieldAccessor);
            } catch (Exception e){
                log.info(
                        String.format(
                                logErrorMsgTemplate,
                                fieldNumber,
                                e.getMessage()
                        )
                );
            }

        }

        return result;
    }

    public Object getValueByFieldName(Object source, String fieldName){
        Object result = null;

        DataFieldAccessor fieldAccessor = getDataFieldAccessorByFieldName(fieldName);

        if (fieldAccessor != null){
            try{
                result = invokeGetter(source, fieldAccessor);
            } catch (Exception e){
                log.info(
                        String.format(
                                logErrorMsgTemplate,
                                fieldName,
                                e.getMessage()
                        )
                );
            }

        }

        return result;
    }

    public int setValueByFieldNumber(Object source, int fieldNumber){
        int result = -1;

        DataFieldAccessor fieldAccessor = getDataFieldAccessorByFieldNumber(fieldNumber);

        if (fieldAccessor != null){
            try{
                invokeSetter(source, fieldAccessor);
                result = 0;
            } catch (Exception e){
                log.info(
                        String.format(
                                logErrorMsgTemplate,
                                fieldNumber,
                                e.getMessage()
                        )
                );
            }

        }

        return result;
    }

    public int setValueByFieldName(Object source, String fieldName){
        int result = -1;

        DataFieldAccessor fieldAccessor = getDataFieldAccessorByFieldName(fieldName);

        if (fieldAccessor != null){
            try{
                invokeSetter(source, fieldAccessor);
                result = 0;
            } catch (Exception e){
                log.info(
                        String.format(
                                logErrorMsgTemplate,
                                fieldName,
                                e.getMessage()
                        )
                );
            }

        }

        return result;
    }

    private static Object invokeGetter(
                                                Object source,
                                                DataFieldAccessor fieldAccessor
                                        )
                            throws
                                    IllegalAccessException,
                                    InvocationTargetException
    {
        return fieldAccessor.getGetter().invoke(source);
    }

    private static void invokeSetter(
            Object source,
            DataFieldAccessor fieldAccessor
    )
            throws
            IllegalAccessException,
            InvocationTargetException
    {
        fieldAccessor.getSetter().invoke(source);
    }

    @Nullable
    private DataFieldAccessor getDataFieldAccessorByFieldNumber(int fieldNumber) {
        DataFieldAccessor fieldAccessor = null;

        AtomicReference<DataFieldDescriptor> descriptorRef = new AtomicReference<>(null);

        fieldAccessorsMap.forEach(
                (descr, access) -> {
                    if (descr.getFieldNumber() == fieldNumber){
                        descriptorRef.set(descr);
                    }
                }
        );


        if (descriptorRef.get() != null){
            fieldAccessor = fieldAccessorsMap.get(descriptorRef.get());
        }
        return fieldAccessor;
    }

    @Nullable
    private DataFieldAccessor getDataFieldAccessorByFieldName(String fieldName) {
        DataFieldAccessor fieldAccessor = null;

        AtomicReference<DataFieldDescriptor> descriptorRef = new AtomicReference<>(null);

        fieldAccessorsMap.forEach(
                (descr, access) -> {
                    if (descr.getName() == fieldName){
                        descriptorRef.set(descr);
                    }
                }
        );


        if (descriptorRef.get() != null){
            fieldAccessor = fieldAccessorsMap.get(descriptorRef.get());
        }
        return fieldAccessor;
    }
}
