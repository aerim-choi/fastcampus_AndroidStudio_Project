package org.techtown.aop_part3_chapter06.chatlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.techtown.aop_part3_chapter06.DBKey.Companion.CHILD_CHAT
import org.techtown.aop_part3_chapter06.DBKey.Companion.DB_USERS
import org.techtown.aop_part3_chapter06.R
import org.techtown.aop_part3_chapter06.databinding.FragmentChatlistBinding
import org.techtown.aop_part3_chapter06.home.ArticleAdapter

class ChatListFragment: Fragment(R.layout.fragment_chatlist) {
    private var binding: FragmentChatlistBinding?=null
    private lateinit var chatListAdapter : ChatListAdapter
    private val chatRoomList = mutableListOf<ChatListItem>()


    private val auth : FirebaseAuth by lazy{
        Firebase.auth
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentChatlistBinding = FragmentChatlistBinding.bind(view)
        binding = fragmentChatlistBinding


        chatListAdapter = ChatListAdapter(onItemClicked = {
            // 채팅방으로 이동 하는 코드

        })

        chatRoomList.clear()

        fragmentChatlistBinding.chatListRecyclerView.adapter = chatListAdapter
        fragmentChatlistBinding.chatListRecyclerView.layoutManager = LinearLayoutManager(context)


        if (auth.currentUser == null) {
            return
        }

        val chatDB = Firebase.database.reference.child(DB_USERS).child(auth.currentUser!!.uid).child(CHILD_CHAT)

        chatDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val model = it.getValue(ChatListItem::class.java)
                    model ?: return

                    chatRoomList.add(model)
                }

                chatListAdapter.submitList(chatRoomList)
                chatListAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })


    }

    override fun onResume() {
        super.onResume()
        chatListAdapter.notifyDataSetChanged()//뷰 갱신
    }
}