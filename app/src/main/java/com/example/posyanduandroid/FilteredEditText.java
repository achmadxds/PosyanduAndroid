package com.example.posyanduandroid;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import org.jetbrains.annotations.NotNull;

public class FilteredEditText extends AppCompatEditText {
  
  private static final String LOG_TAG = FilteredEditText.class.getSimpleName();
  private String expectedString = null;
  
  public FilteredEditText(Context context) {
    super(context);
  }

  public FilteredEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public FilteredEditText(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }
  
  public void setExpectedString(@NonNull String value) {
    this.expectedString = value;
    this.setupInputFilter();
  }

  private void setupInputFilter() {
    this.setFilters(new InputFilter[] {
      new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
          if (source.length() > 0 && source.charAt(end-1) == expectedString.charAt(dend)) {

            /* valid input received */
            Log.d(LOG_TAG, "input accepted: " + String.valueOf(source.charAt(end-1)));
            return source;

          } else {

            /* invalid input received */
            Log.d(LOG_TAG, "input rejected: " + String.valueOf(source.charAt(end-1)) + " - expected: " + String.valueOf(expectedString.charAt(dend)));
            return "";
          }
        }
      }
    });
  }

  /** hardware event  */
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    Log.d(LOG_TAG, "onKeyDown()");
    return super.onKeyDown(keyCode, event);
  }

  /** hardware event  */
  @Override
  public boolean onKeyUp(int keyCode, KeyEvent event) {
    Log.d(LOG_TAG, "onKeyUp()");
    return super.onKeyUp(keyCode, event);
  }
}
