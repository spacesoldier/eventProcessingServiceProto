package com.leaderhackdemo.servicerequests.features.parsedataset.model.data;

import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>
 * This interface helps to kindly introduce a tricky stuff in this application
 * It should point to the class which fields are named according to the following convention:
 * field[delimiter]{fieldNumber}[delimiter]{fieldName}
 * </p>
 * <p> </p>
 * The numeration starts with 0
 * </p>
 * <p> </p>
 * <p>
 * For example let the delimiter be "_", then the class will look like :
 * </p>
 * <p>
 * <p><b>class</b> MyAwesomeNumeratedClass <b>implements</b> NumeratedFieldNamingClass{</p>
 * <p>     &emsp Object field_0_TheVeryFirstField;</p>
 * <p>     &emsp Object field_1_SecondField;</p>
 * <p>     &emsp // and so on</p>
 * <p>     &emsp Object field_1111_SomeVeryImportantFieldAtTheEndOfTheLongLongRow;</p>
 * <p></p>
 * <p>     &emsp // we kindly ask to implement one weird method</p>
 * <p>     &emsp // just to ensure the user of this trick had read and understood the idea</p>
 * <p>     &emsp @Override</p>
 * <p>     &emsp <b>public</b> List<Method> getMethods() {</p>
 * <p>     &emsp &emsp   return List.of(this.getClass().getMethods());</p>
 * <p>     &emsp }</p>
 * <p>}</p>
 * </p>
 * <p>
 *     This interface could be used for construction of the data classes with the very large number of fields.
 *     For example to represent the data rows from the tables which have many columns.
 *     Though further field manipulation could be done in automated manner
 * </p>
 */
public interface NumeratedFieldNamingClass {
    List<Method> getMethods();
}
