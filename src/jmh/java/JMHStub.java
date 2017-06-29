/*
 *    Copyright 2017 David Tesler
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class JMHMultiKeyMapVsMap {

    @Param({"1000"})
    public int readContainerSize = 1000;

    @Param({"10"})
    public int keySize = 10;

    @Param({"10"})
    public int subKeySize = 10;

    @Param({"100"})
    public int containerSize = 100;

    private Random random;

    public static void main(final String[] args) throws RunnerException {
        final Options opt = new OptionsBuilder().include(JMHStub.class.getSimpleName()).build();
        new Runner(opt).run();
    }

    @Setup(Level.Trial)
    public void trialSetup() {
        random = new Random();
    }

    @Setup(Level.Iteration)
    public void iterationSetup() {
    }

    @Benchmark()
    public Integer baseline_helloWorld() {
        return "Hello World!";
    }
}
