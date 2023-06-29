package com.hj.firebasechat.model

class Message {
    var id: String = ""
    var msg: String = ""
    var userName: String = ""
    var timestamp: Long = 0

    constructor()

    constructor(msg: String, userName: String) {
        this.msg = msg
        this.userName = userName
        this.timestamp = System.currentTimeMillis()
    }

}