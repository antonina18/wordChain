package com.word.chain.app;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class ChainCalculatorTest {

    private ChainCalculator chainCalculator;

    @Test
    public void calculate() throws Exception {
        //given
        int expected = 4;
        Map<Integer, List<String>> hashMap = new HashMap<>();
        hashMap.put(3, asList("dog", "cog", "cat", "cot"));
        chainCalculator = new ChainCalculator(hashMap);

        //when
        List<String> result = chainCalculator.calculate("dog", "cat");

        //then
        assertTrue(!result.isEmpty());
        assertThat(result.size(), is(equalTo(expected)));
    }

    @Test
    public void calculateNoChain() throws Exception {
        //given
        Map<Integer, List<String>> hashMap = new HashMap<>();
        hashMap.put(3, asList("dog", "cog", "cat"));
        chainCalculator = new ChainCalculator(hashMap);

        //when
        List<String> result = chainCalculator.calculate("dog", "cat");

        //then
        assertTrue(result.isEmpty());
    }

}