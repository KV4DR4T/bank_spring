//package com.example.bank_spring.model;
//
//import lombok.Data;
//
//
//public enum Status {
//    ACTIVE(0,"ACTIVE"),NOT_ACTIVE(1,"NOT_ACTIVE"),DELETED(2,"DELETED");
//    private Integer id;
//    private String name;
//
//    Status(int id, String name) {
//        this.id=id;
//        this.name=name;
//    }
//
//    public static Status statusFromId(Integer id){
//        for(Status o:Status.values()){
//            if(o.id.compareTo(id)==0){
//                return o;
//            }
//        }
//        return null;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//}
