package com.github.infomaniak.dragdropswiperecyclerviewsample.feature.managelog.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.infomaniak.dragdropswiperecyclerviewsample.R
import com.github.infomaniak.dragdropswiperecyclerviewsample.util.Logger

/**
 * This fragment shows all the logged messages.
 */
class LogFragment : Fragment() {

    private var messagesViewContainerContainer: ScrollView? = null
    private var messagesView: TextView? = null

    private val onLogUpdateListener = object : Logger.OnLogUpdateListener {
        override fun onLogUpdated() {
            loadLogMessages()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_log, container, false)
        messagesViewContainerContainer = rootView.findViewById(R.id.messages_container_container)
        messagesView = rootView.findViewById(R.id.messages)

        loadLogMessages()

        return rootView
    }

    override fun onResume() {
        super.onResume()

        Logger.addListener(onLogUpdateListener)
    }

    override fun onPause() {
        super.onPause()

        Logger.removeListener(onLogUpdateListener)
    }

    private fun loadLogMessages() {
        messagesView?.text = getString(R.string.empty_log)

        if (Logger.instance?.messages?.isNotEmpty() == true) {
            messagesView?.text = ""
            Logger.instance?.messages?.forEachIndexed { index, message -> messagesView?.append("${index + 1}. $message\n\n") }
            messagesViewContainerContainer?.post { messagesViewContainerContainer?.fullScroll(View.FOCUS_DOWN) }
        }
    }

    companion object {
        const val TAG = "LogFragment"

        fun newInstance() = LogFragment()
    }
}
