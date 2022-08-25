// Generated by view binder compiler. Do not edit!
package com.example.renthouse.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.renthouse.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentProfileBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final Button Edit;

  @NonNull
  public final ImageView imageView4;

  @NonNull
  public final LinearLayout linearLayout2;

  @NonNull
  public final EditText profileCity;

  @NonNull
  public final EditText profileConfirm;

  @NonNull
  public final Spinner profileCountry;

  @NonNull
  public final EditText profileName;

  @NonNull
  public final EditText profilePassword;

  @NonNull
  public final EditText profilePhone;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView18;

  @NonNull
  public final TextView textView19;

  @NonNull
  public final TextView textView20;

  @NonNull
  public final TextView textView21;

  @NonNull
  public final TextView textView22;

  @NonNull
  public final TextView textView23;

  private FragmentProfileBinding(@NonNull FrameLayout rootView, @NonNull Button Edit,
      @NonNull ImageView imageView4, @NonNull LinearLayout linearLayout2,
      @NonNull EditText profileCity, @NonNull EditText profileConfirm,
      @NonNull Spinner profileCountry, @NonNull EditText profileName,
      @NonNull EditText profilePassword, @NonNull EditText profilePhone,
      @NonNull TextView textView10, @NonNull TextView textView18, @NonNull TextView textView19,
      @NonNull TextView textView20, @NonNull TextView textView21, @NonNull TextView textView22,
      @NonNull TextView textView23) {
    this.rootView = rootView;
    this.Edit = Edit;
    this.imageView4 = imageView4;
    this.linearLayout2 = linearLayout2;
    this.profileCity = profileCity;
    this.profileConfirm = profileConfirm;
    this.profileCountry = profileCountry;
    this.profileName = profileName;
    this.profilePassword = profilePassword;
    this.profilePhone = profilePhone;
    this.textView10 = textView10;
    this.textView18 = textView18;
    this.textView19 = textView19;
    this.textView20 = textView20;
    this.textView21 = textView21;
    this.textView22 = textView22;
    this.textView23 = textView23;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Edit;
      Button Edit = ViewBindings.findChildViewById(rootView, id);
      if (Edit == null) {
        break missingId;
      }

      id = R.id.imageView4;
      ImageView imageView4 = ViewBindings.findChildViewById(rootView, id);
      if (imageView4 == null) {
        break missingId;
      }

      id = R.id.linearLayout2;
      LinearLayout linearLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout2 == null) {
        break missingId;
      }

      id = R.id.profile_city;
      EditText profileCity = ViewBindings.findChildViewById(rootView, id);
      if (profileCity == null) {
        break missingId;
      }

      id = R.id.profile_confirm;
      EditText profileConfirm = ViewBindings.findChildViewById(rootView, id);
      if (profileConfirm == null) {
        break missingId;
      }

      id = R.id.profile_country;
      Spinner profileCountry = ViewBindings.findChildViewById(rootView, id);
      if (profileCountry == null) {
        break missingId;
      }

      id = R.id.profile_name;
      EditText profileName = ViewBindings.findChildViewById(rootView, id);
      if (profileName == null) {
        break missingId;
      }

      id = R.id.profile_password;
      EditText profilePassword = ViewBindings.findChildViewById(rootView, id);
      if (profilePassword == null) {
        break missingId;
      }

      id = R.id.profile_phone;
      EditText profilePhone = ViewBindings.findChildViewById(rootView, id);
      if (profilePhone == null) {
        break missingId;
      }

      id = R.id.textView10;
      TextView textView10 = ViewBindings.findChildViewById(rootView, id);
      if (textView10 == null) {
        break missingId;
      }

      id = R.id.textView18;
      TextView textView18 = ViewBindings.findChildViewById(rootView, id);
      if (textView18 == null) {
        break missingId;
      }

      id = R.id.textView19;
      TextView textView19 = ViewBindings.findChildViewById(rootView, id);
      if (textView19 == null) {
        break missingId;
      }

      id = R.id.textView20;
      TextView textView20 = ViewBindings.findChildViewById(rootView, id);
      if (textView20 == null) {
        break missingId;
      }

      id = R.id.textView21;
      TextView textView21 = ViewBindings.findChildViewById(rootView, id);
      if (textView21 == null) {
        break missingId;
      }

      id = R.id.textView22;
      TextView textView22 = ViewBindings.findChildViewById(rootView, id);
      if (textView22 == null) {
        break missingId;
      }

      id = R.id.textView23;
      TextView textView23 = ViewBindings.findChildViewById(rootView, id);
      if (textView23 == null) {
        break missingId;
      }

      return new FragmentProfileBinding((FrameLayout) rootView, Edit, imageView4, linearLayout2,
          profileCity, profileConfirm, profileCountry, profileName, profilePassword, profilePhone,
          textView10, textView18, textView19, textView20, textView21, textView22, textView23);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}