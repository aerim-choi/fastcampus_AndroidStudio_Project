package org.techtown.aop_part3_chapter06.chatlist

data class ChatListItem (

    val buyerId:String,
    val sellerId :String,
    val itemTitle:String, //채팅방 이름
    val key:Long            //시간으로 키값을 정함
    ){
    constructor():this("","","",0)
}