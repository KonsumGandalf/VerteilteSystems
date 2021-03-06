/*
 * Copyright (c) 2008-2013, Hazelcast, Inc. All Rights Reserved.
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

package java.com.hazelcast.example.mapreduce;

import com.hazelcast.mapreduce.Collator;

import java.util.Map;

public class WordCountCollator
        implements Collator<Map.Entry<String, Long>, Long> {

    @Override
    public Long collate(Iterable<Map.Entry<String, Long>> values) {
        long sum = 0;

        // Just sum up all resulting numbers to calculate the overall amount of words
        for (Map.Entry<String, Long> entry : values) {
            sum += entry.getValue().longValue();
        }

        return sum;
    }
}
