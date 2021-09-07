package com.louisngatale.twitterclone.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.louisngatale.twitterclone.repository.BaseRepository

abstract class BaseFragment<VM: ViewModel, R: BaseRepository> : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun getViewModel():Class<VM>

    abstract fun getFragmentRepository() : R
}