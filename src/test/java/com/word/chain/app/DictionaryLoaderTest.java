package com.word.chain.app;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;

public class DictionaryLoaderTest {

    private DictionaryLoader dictionaryLoader;

    @Test
    public void testLoad() throws Exception {
        //given
        dictionaryLoader = new DictionaryLoader("src/test/resources/dictionary.txt");

        //when
        Map<Integer, List<String>> result = dictionaryLoader.load();

        //then
        assertThat(result.entrySet().size(), is(equalTo(2)));
        assertNotNull(result.get(3));
        assertThat(result.get(3).size(), is(equalTo(2)));
        assertNotNull(result.get(4));
        assertThat(result.get(4).size(), is(equalTo(2)));

    }

    @Test
    public void testLoadFailure() throws Exception {
        //given
        dictionaryLoader = new DictionaryLoader("src/test/resources/wrong.txt");

        //when
        Map<Integer, List<String>> result = dictionaryLoader.load();

        //then
        assertThat(result.entrySet().size(), is(equalTo(0)));
    }

}