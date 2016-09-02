package com.anysoftkeyboard.ime;

import com.anysoftkeyboard.AnySoftKeyboardBaseTest;
import com.anysoftkeyboard.SharedPrefsHelper;
import com.anysoftkeyboard.api.KeyCodes;
import com.menny.android.anysoftkeyboard.R;

import org.junit.Assert;
import org.junit.Test;

public class AnySoftKeyboardKeyboardTagsSearcherTest extends AnySoftKeyboardBaseTest {
    @Test
    public void testOnSharedPreferenceChangedCauseLoading() throws Exception {
        Assert.assertNotNull(mAnySoftKeyboardUnderTest.getQuickTextTagsSearcher());
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_search_quick_text_tags, false);
        Assert.assertNull(mAnySoftKeyboardUnderTest.getQuickTextTagsSearcher());
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_search_quick_text_tags, false);
        Assert.assertNull(mAnySoftKeyboardUnderTest.getQuickTextTagsSearcher());
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_search_quick_text_tags, true);
        Object searcher = mAnySoftKeyboardUnderTest.getQuickTextTagsSearcher();
        Assert.assertNotNull(searcher);
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_search_quick_text_tags, true);
        Assert.assertSame(searcher, mAnySoftKeyboardUnderTest.getQuickTextTagsSearcher());
    }

    @Test
    public void testUnrelatedOnSharedPreferenceChangedDoesNotCreateSearcher() throws Exception {
        Object searcher = mAnySoftKeyboardUnderTest.getQuickTextTagsSearcher();
        Assert.assertNotNull(searcher);
        //unrelated pref change, should not create a new searcher
        SharedPrefsHelper.setPrefsValue(R.string.settings_key_allow_suggestions_restart, false);
        Assert.assertSame(searcher, mAnySoftKeyboardUnderTest.getQuickTextTagsSearcher());

        SharedPrefsHelper.setPrefsValue(R.string.settings_key_search_quick_text_tags, false);
        Assert.assertNull(mAnySoftKeyboardUnderTest.getQuickTextTagsSearcher());

        SharedPrefsHelper.setPrefsValue(R.string.settings_key_allow_suggestions_restart, true);
        Assert.assertNull(mAnySoftKeyboardUnderTest.getQuickTextTagsSearcher());
    }

    @Test
    public void testEnabledTypingTagProvidesSuggestionsFromTagsOnly() throws Exception {

    }

    @Test
    public void testTagsSearchingStopsWhenDeletingColon() throws Exception {
        verifyNoSuggestionsInteractions();
        mAnySoftKeyboardUnderTest.simulateKeyPress(':');
        verifySuggestions(true, ":");
        mAnySoftKeyboardUnderTest.simulateTextTyping("face");
        verifySuggestions(true, ":face", "&#128512;");
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        mAnySoftKeyboardUnderTest.simulateKeyPress(KeyCodes.DELETE);
        verifySuggestions(true, "");
        mAnySoftKeyboardUnderTest.simulateKeyPress('f');
        verifySuggestions(true, "f", "face");
    }

    @Test
    public void testDisabledTypingTagDoesNotProvidesSuggestions() throws Exception {
        Assert.fail();
    }

    @Test
    public void testQuickTextEnabledPluginsPrefsChangedCauseReload() throws Exception {
        Assert.fail();
    }

}