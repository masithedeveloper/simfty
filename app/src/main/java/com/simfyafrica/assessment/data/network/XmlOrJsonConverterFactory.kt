//package com.simfyafrica.assessment.data.network
//
//import android.util.Xml
//import com.google.gson.GsonBuilder
//import okhttp3.ResponseBody
//import org.simpleframework.xml.convert.AnnotationStrategy
//import org.simpleframework.xml.core.Persister
//import org.simpleframework.xml.strategy.Type
//import retrofit2.Converter
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//
//internal class XmlOrJsonConverterFactory : Converter.Factory() {
//    fun responseBodyConverter(
//        type: Type?,
//        annotations: Array<Annotation>,
//        retrofit: Retrofit?
//    ): Converter<ResponseBody, *>? {
//        for (annotation in annotations) {
//            if (annotation.annotationType() == Xml::class.java) {
//                return SimpleXmlConverterFactory.createNonStrict(
//                    Persister(AnnotationStrategy())
//                ).responseBodyConverter(type, annotations, retrofit)
//            }
//            if (annotation.annotationType() == Json::class.java) {
//                return GsonConverterFactory.create(
//                    GsonBuilder().setLenient().excludeFieldsWithoutExposeAnnotation().create()
//                ).responseBodyConverter(type, annotations, retrofit)
//            }
//        }
//        return GsonConverterFactory.create(
//            GsonBuilder().setLenient().excludeFieldsWithoutExposeAnnotation().create()
//        ).responseBodyConverter(type, annotations, retrofit)
//    }
//}