/*
 * Copyright (c) 2016-2019 Zerocracy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zerocracy.pm.staff.ranks;

import com.zerocracy.FkProject;
import com.zerocracy.pm.scope.Wbs;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link RnkRev}.
 *
 * @since 1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class RnkRevTest {
    @Test
    public void order() throws Exception {
        final FkProject project = new FkProject();
        final Wbs wbs = new Wbs(project).bootstrap();
        final String first = "gh:test/test#1";
        final List<String> list = Arrays.asList(
            "gh:test/test#2",
            first,
            "gh:test/test#3"
        );
        for (final String job : list) {
            wbs.add(job);
        }
        wbs.role(first, "REV");
        list.sort(new RnkRev(wbs));
        MatcherAssert.assertThat(
            "REV job is not first",
            list.get(0),
            Matchers.equalTo(first)
        );
    }
}
