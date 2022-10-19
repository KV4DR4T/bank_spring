//package com.example.bank_spring.security;
//
//import com.example.bank_spring.model.Role;
//import com.example.bank_spring.model.Status;
//
//import javax.persistence.AttributeConverter;
//import javax.persistence.Convert;
//import javax.persistence.Converter;
//
//public class StatusConverter {
//    @Converter(autoApply = true)
//    public static class FieldConverter implements AttributeConverter<Status,Integer>{
//
//        @Override
//        public Integer convertToDatabaseColumn(Status status) {
//            return status.getId();
//        }
//
//        @Override
//        public Status convertToEntityAttribute(Integer integer) {
//            return Status.statusFromId(integer);
//        }
//    }
//}
