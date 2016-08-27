package com.anysoftkeyboard.keyboards;

import com.anysoftkeyboard.api.KeyCodes;
import com.menny.android.anysoftkeyboard.R;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

@RunWith(RobolectricTestRunner.class)
public class ExternalAnyKeyboardTest {
    public static final KeyboardDimens SIMPLE_KeyboardDimens = new KeyboardDimens() {
        @Override
        public int getKeyboardMaxWidth() {
            return 120;
        }

        @Override
        public int getKeyMaxWidth() {
            return 10;
        }

        @Override
        public float getKeyHorizontalGap() {
            return 1;
        }

        @Override
        public float getRowVerticalGap() {
            return 2;
        }

        @Override
        public int getNormalKeyHeight() {
            return 5;
        }

        @Override
        public int getSmallKeyHeight() {
            return 4;
        }

        @Override
        public int getLargeKeyHeight() {
            return 6;
        }
    };

    @Test
    public void testGeneralProperties() throws Exception {
        AnyKeyboard keyboard = KeyboardFactory.getAllAvailableKeyboards(RuntimeEnvironment.application).get(0).createKeyboard(RuntimeEnvironment.application, 1);
        Assert.assertNotNull(keyboard);
        Assert.assertTrue(keyboard instanceof ExternalAnyKeyboard);
        Assert.assertEquals("en", keyboard.getDefaultDictionaryLocale());
        Assert.assertEquals("English", keyboard.getKeyboardName());
        Assert.assertEquals("keyboard_c7535083-4fe6-49dc-81aa-c5438a1a343a", keyboard.getKeyboardPrefId());
        Assert.assertEquals(R.drawable.ic_stat_en, keyboard.getKeyboardIconResId());
        Assert.assertEquals(1, keyboard.getKeyboardMode());
    }

    @Test
    public void testLoadedKeyboard() throws Exception {
        AnyKeyboard keyboard = KeyboardFactory.getAllAvailableKeyboards(RuntimeEnvironment.application).get(0).createKeyboard(RuntimeEnvironment.application, 1);
        Assert.assertNotNull(keyboard);
        keyboard.loadKeyboard(SIMPLE_KeyboardDimens);

        Assert.assertEquals(10 * SIMPLE_KeyboardDimens.getKeyMaxWidth(), keyboard.getMinWidth());
        Assert.assertEquals(44, keyboard.getHeight());
        Assert.assertEquals(40, keyboard.getKeys().size());
        Assert.assertEquals(KeyCodes.SHIFT, keyboard.getKeys().get(keyboard.getShiftKeyIndex()).codes[0]);
    }
}