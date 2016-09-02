/*
 * Copyright (c) 2016 Menny Even-Danan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.anysoftkeyboard.ime;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.anysoftkeyboard.keyboards.Keyboard;
import com.anysoftkeyboard.quicktextkeys.QuickTextKey;
import com.anysoftkeyboard.quicktextkeys.QuickTextKeyFactory;
import com.anysoftkeyboard.quicktextkeys.TagsExtractor;
import com.menny.android.anysoftkeyboard.R;

import java.util.List;

public abstract class AnySoftKeyboardKeyboardTagsSearcher extends AnySoftKeyboardKeyboardSwitchedListener {

    @NonNull
    private String mTagExtractorPrefKey;

    @Nullable
    private TagsExtractor mEmojiTagsSearcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mTagExtractorPrefKey = getString(R.string.settings_key_search_quick_text_tags);
        updateTagExtractor(PreferenceManager.getDefaultSharedPreferences(this));
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (mTagExtractorPrefKey.equals(key)) {
            updateTagExtractor(sharedPreferences);
        }
    }

    private void updateTagExtractor(SharedPreferences sharedPreferences) {
        final boolean enabled = sharedPreferences.getBoolean(mTagExtractorPrefKey, false);
        if (enabled && mEmojiTagsSearcher == null) {
            mEmojiTagsSearcher = new TagsExtractor(extractKeysListListFromEnabledQuickText(QuickTextKeyFactory.getOrderedEnabledQuickKeys(getApplicationContext())));
        } else if (!enabled){
            mEmojiTagsSearcher = null;
        }
    }

    @Nullable
    protected TagsExtractor getQuickTextTagsSearcher() {
        return mEmojiTagsSearcher;
    }

    private List<List<Keyboard.Key>> extractKeysListListFromEnabledQuickText(List<QuickTextKey> orderedEnabledQuickKeys) {
    }
}
