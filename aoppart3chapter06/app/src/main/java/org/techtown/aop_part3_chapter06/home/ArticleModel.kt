package org.techtown.aop_part3_chapter06.home

data class ArticleModel(

    var sellerId : String,
    val title : String,
    val createdAt : Long,
    val price : String,
    val imageUrl : String

){
    constructor():this("","",0,"","")
}
