/*
 * Copyright (C) 2018 AlexMofer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package am.project.x.business.others.font;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import am.project.x.R;
import am.project.x.base.BaseActivity;

/**
 * 字体
 */
public class FontActivity extends BaseActivity implements FontView,
        FontFamilyPickerDialog.OnPickerListener {

    private final FontPresenter mPresenter = new FontPresenter(this);
    private FontFamilyPickerDialog mPicker;

    public static void start(Context context) {
        context.startActivity(new Intent(context, FontActivity.class));
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_font;
    }

    @Override
    protected void initializeActivity(@Nullable Bundle savedInstanceState) {
        setSupportActionBar(R.id.font_toolbar);

        showLoading();
        mPresenter.loadConfig();
    }

    @Override
    protected FontPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_font, menu);
        final MenuItem item = menu.findItem(R.id.font_family);
        item.setVisible(mPresenter.getFamilyNameOrAliaCount() > 0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.font_family:
                if (mPresenter.getFamilyNameOrAliaCount() > 0)
                    showPicker(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // View
    @Override
    public void onLoadConfigFailure() {
        dismissLoading();
        Toast.makeText(this, R.string.font_error, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onLoadConfigSuccess() {
        dismissLoading();
        invalidateOptionsMenu();
        showPicker(false);
    }

    // Listener
    @Override
    public void onItemPicked(String item) {
        dismissDialog(mPicker);
    }

    private void showPicker(boolean cancelable) {
        if (mPicker == null)
            mPicker = new FontFamilyPickerDialog(this, mPresenter, this);
        mPicker.notifyDataSetChanged();
        mPicker.setCancelable(cancelable);
        mPicker.setCanceledOnTouchOutside(cancelable);
        showDialog(mPicker);
    }
}
