package com.leaderhackdemo.servicerequests.features.parsedataset.model.data;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

@Data @Builder
public class ServiceEventRaw implements NumeratedFieldNamingClass{
    private String field_0_RequestRootId;                        //    Корневой ИД заявки
    private String field_1_RequestVersionId;                     //    ИД версии заявки
    private String field_2_RequestId;                            //    Номер заявки
    private String field_3_UIdGUrequest;                         //    Уникальный номер обращения ГУ (mos.ru)
    private String field_4_RqCreationDateTZ;                     //    Дата создания заявки в формате Timezone
    private String field_5_RqVersionDateStartTZ;                 //    Дата начала действия версии заявки в формате Timezone
    private String field_6_RequestSourceName;                    //    Наименование источника поступления заявки
    private String field_7_RequestSourceCode;                    //    Код источника поступления заявки
    private String field_8_RequestAuthorName;                    //    Имя создателя заявки
    private String field_9_IsIncident;                          //    Признак инцидента
    private String field_10_ParentRequestRooId;                  //    Корневой идентификатор материнской заявки
    private String field_11_ParentRequestId;                     //    Номер материнской заявки
    private String field_12_LastUpdateUsername;                  //    Пользователь, внесший последнее изменение
    private String field_13_UserOrganizationRole;                //    Роль организации пользователя
    private String field_14_Comments;                            //            Комментарии
    private String field_15_DefectCategoryName;                  //    Наименование категории дефекта
    private String field_16_DefectCategoryId;                    //    Корневой идентификатор категории дефекта
    private String field_17_DefectCategoryCode;                  //    Код категории дефекта
    private String field_18_DefectName;                          //    Наименование дефекта
    private String field_19_DefectShortName;                     //    Краткое наименование дефекта
    private String field_20_DefectId;                            //    Идентификатор дефекта
    private String field_21_DefectCode;                          //    Код дефекта
    private String field_22_IsDefectReturnedToWork;              //    Признак дефекта возврата на доработку
    private String field_23_Description;                         //            Описание
    private String field_24_ApplicantHasQuestion;                //    Наличие у заявителя вопроса
    private String field_25_SeverityCategoryName;                //    Наименование категории срочности: Аварийная, Обычная
    private String field_26_SeverityCategoryCode;                //    Код категории срочности
    private String field_27_DistrictName;                        //    Наименование округа
    private String field_28_DistrictCode;                        //    Код округа
    private String field_29_AreaName;                            //    Наименование района
    private String field_30_AreaCode;                            //    Код района
    private String field_31_ProblemAddress;                      //    Адрес проблемы
    private String field_32_UNOM;                                //    УНОМ
    private String field_33_Entrance;                            //            Подъезд
    private String field_34_Floor;                               //    Этаж
    private String field_35_Apartment;                           //            Квартира
    private String field_36_UnitedDispatcherServiceNumber;       //    ОДС (номер объединенной диспетчерской службы)
    private String field_37_ManagementCompanyName;               //    Наименование управляющей компании
    private String field_38_ContractorOrgName;                   //    Наименование обслуживавшей организации (исполнителя)
    private String field_39_ContractorOrgId;                     //    Идентификатор организации-исполнителя
    private String field_40_ContractorOrgINN;                    //    ИНН организации-исполнителя
    private String field_41_RequestStatusName;                   //    Наименование статуса заявки
    private String field_42_RequestStatusCode;                   //    Код статуса заявки
    private String field_43_ContractorDeclineReasonName;         //    Наименование причины отказа исполнителя
    private String field_44_ContractorDeclineReasonCode;         //    Идентификатор причины отказа исполнителя
    private String field_45_ContractorOrgDeclineReasonName;      //    Наименование причины отказа Организации-исполнителя
    private String field_46_ContractorOrgDeclineReasonId;        //    Идентификатор причины отказа Организации-исполнителя
    private String field_47_WorksDoneType;                       //    Вид выполненных работ
    private String field_48_WorksDoneRootVersionId;              //    Идентификатор корневой версии вида выполненных работ
    private String field_49_MaterialsSpent;                      //    Израсходованный материал
    private String field_50_ProtectiveMeasuresName;              //    Наименование охранных мероприятий
    private String field_51_ProtectiveMeasuresRootVersionId;     //    Идентификатор корневой версии охранных мероприятий
    private String field_52_Effectiveness;                       //            Результативность
    private String field_53_EffectivenessCode;                   //    Код результативности
    private String field_54_ReturnToWorkCount;                   //    Кол-во возвратов на доработку
    private String field_55_ReturnToWorkLastDate;                //    Дата последнего возврата на доработку
    private String field_56_IsInReworkState;                     //    Признак нахождения на доработке
    private String field_57_IsAlerted;                           //    Признак “Оповещен”
    private String field_58_CloseDate;                           //    Дата закрытия
    private String field_59_DesiredTimeFrom;                     //    Желаемое время с
    private String field_60_DesiredTimeTo;                       //    Желаемое время до
    private String field_61_FeedbackOrRateDate;                  //    Дата отзыва/оценки
    private String field_62_Feedback;                            //            Отзыв$
    private String field_63_QualityRate;                         //    Оценка качества выполнения работ
    private String field_64_QualityRateCode;                     //    Код оценки качества выполнения ра
    private String field_65_PayableCategoryName;                 //    Наименование категории платности
    private String field_66_PayableCategoryCode;                 //    Код категории платности
    private String field_67_IsCardPaymentPerformed;              //    Признак оплаты картой

    @Override
    public List<Method> getMethods() {
        return List.of(this.getClass().getMethods());
    }

}
