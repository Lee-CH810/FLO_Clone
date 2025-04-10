package com.example.flo_clone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.collection.intListOf
import com.example.flo_clone.databinding.FragmentAlbumBinding
import com.google.gson.Gson

class AlbumFragment : Fragment() {

    lateinit var binding: FragmentAlbumBinding
    private var gson: Gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        // HomeFragment에서 생성한 argument에 들어있는 Json형태의 데이터를 다시 album 객체로 변환
        val albumJson = arguments?.getString("album")
        val album = gson.fromJson(albumJson, Album::class.java)
        setInit(album)

        /**
         * 뒤로 가기
         */
        binding.albumBackIv.setOnClickListener {
            // context sd : MainActivity 안에 있는 Fragment를 어디서 변경할 건지 명시하는 것.
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }

        /**
         * 앨범의 수록곡을 눌렀을 때 수록곡의 제목이 나오도록 함
         */
        binding.songLalacLayout.setOnClickListener {
            // Toast.makeText(...).show()를 통해 토스트 메세지를 띄울 수 있음.
            // 토스트 메시지를 어디서 띄울 것인지(activity--> albumFragment가 있는 곳은 activity이기 때문)와
            // 띄울 메세지, 얼마나 띄울 것인지(짧게 띄울 것이므로 LENGTH_SHORT)를 인자로 넘겨줌
            Toast.makeText(activity, "LILAC", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    /**
     * 넘겨받은 album 객체의 정보로 AlbumFragement에서의 앨범 제목, 가수명, 앨범 이미지를 재설정
     */
    private fun setInit(album: Album) {
        binding.albumAlbumIv.setImageResource(album.coverImg!!)
        binding.albumMusicTitleTv.text = album.title.toString()
        binding.albumSingerNameTv.text = album.singer.toString()
    }
}