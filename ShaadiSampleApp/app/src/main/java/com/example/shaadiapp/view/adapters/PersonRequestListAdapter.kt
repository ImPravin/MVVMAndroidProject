package com.example.shaadiapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.shaadiapp.R
import com.example.shaadiapp.domain.entities.PersonRequestEntity
import com.example.shaadiapp.view.viewmodel.PersonRequestListModel
import kotlinx.android.synthetic.main.request_item_layout.view.*

class PersonRequestListAdapter(
    private val viewModel: PersonRequestListModel,
    private val users: ArrayList<PersonRequestEntity>
) :
    ListAdapter<PersonRequestEntity, PersonRequestListAdapter.DataViewHolder>(RequestComparator()) {

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: PersonRequestEntity, position: Int) {
            itemView.apply {
                textViewUserName.text = String.format("%s%6s", user.name.first, user.name.last)
                textViewUserInfo.text = String.format(
                    "%s,%6s,\n %s,%6s",
                    user.dob.age,
                    user.location.city,
                    user.location.state,
                    user.location.country
                )
                Glide.with(imageViewAvatar.context)
                    .load(user.picture.medium)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageViewAvatar)
                when (user.status) {
                    0 -> {
                        acceptedTextView.visibility = View.VISIBLE
                        acceptInviteImageView.visibility = View.GONE
                        declineInviteImageView.visibility = View.GONE
                    }
                    1 -> {
                        declinedTextView.visibility = View.VISIBLE
                        acceptInviteImageView.visibility = View.GONE
                        declineInviteImageView.visibility = View.GONE
                    }
                    2 -> {
                        acceptedTextView.visibility = View.GONE
                        declinedTextView.visibility = View.GONE
                        acceptInviteImageView.visibility = View.VISIBLE
                        declineInviteImageView.visibility = View.VISIBLE
                    }
                }
                acceptInviteImageView.setOnClickListener {
                    user.status = 0
                    viewModel.updateRequestStatus(user)
                    acceptedTextView.visibility = View.VISIBLE
                    acceptInviteImageView.visibility = View.GONE
                    declineInviteImageView.visibility = View.GONE
                    notifyItemChanged(position)
                }
                declineInviteImageView.setOnClickListener {
                    user.status = 1
                    viewModel.updateRequestStatus(user)
                    declinedTextView.visibility = View.VISIBLE
                    acceptInviteImageView.visibility = View.GONE
                    declineInviteImageView.visibility = View.GONE
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.request_item_layout, parent, false)
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position], position)
    }

    fun addUsers(users: List<PersonRequestEntity>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }

    class RequestComparator : DiffUtil.ItemCallback<PersonRequestEntity>() {
        override fun areItemsTheSame(
            oldItem: PersonRequestEntity,
            newItem: PersonRequestEntity
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: PersonRequestEntity,
            newItem: PersonRequestEntity
        ): Boolean {
            return oldItem.email == newItem.email
        }
    }
}