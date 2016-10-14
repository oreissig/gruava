package com.github.oreissig.gruava

import com.google.common.base.Preconditions
import com.google.common.base.Strings
import com.google.common.base.Throwables
import com.google.common.collect.HashMultimap
import com.google.common.collect.HashMultiset
import com.google.common.collect.Iterables
import com.google.common.collect.Iterators
import com.google.common.collect.Lists
import com.google.common.collect.Maps
import com.google.common.collect.Multimaps
import com.google.common.collect.Multisets
import com.google.common.collect.Queues
import com.google.common.collect.Sets
import com.google.common.collect.Tables
import com.google.common.collect.TreeBasedTable
import com.google.common.io.Closeables
import com.google.common.io.Files
import com.google.common.io.Flushables
import com.google.common.io.Resources
import com.google.common.net.InetAddresses
import com.google.common.primitives.Booleans
import com.google.common.primitives.Bytes
import com.google.common.primitives.Chars
import com.google.common.primitives.Doubles
import com.google.common.primitives.Floats
import com.google.common.primitives.Ints
import com.google.common.primitives.Longs
import com.google.common.primitives.Shorts
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.Uninterruptibles
import groovy.transform.CompileStatic
import spock.lang.IgnoreIf
import spock.lang.Specification
import spock.lang.Unroll

import java.math.RoundingMode

@Unroll
class GruavaSpec extends Specification {

    def '#utilClass.name is available in Groovy'(utilClass, method, object) {
        when:
        def utilCall = eval { utilClass."$method"(object) }
        def extendedCall = eval { object."$method"() }

        then:
        utilCall?.class == extendedCall?.class
        utilCall?.toString() == extendedCall?.toString()

        where:
        utilClass        | method                 | object
        Strings          | 'isNullOrEmpty'        | ''
        Preconditions    | 'checkNotNull'         | new Object()
        Throwables       | 'getRootCause'         | new Exception()
        Iterables        | 'unmodifiableIterable' | ['foo']
        Iterators        | 'unmodifiableIterator' | [].iterator()
        Lists            | 'newLinkedList'        | ['foo', 'bar']
        Maps             | 'unmodifiableMap'      | [foo: 'bar']
        Multimaps        | 'unmodifiableMultimap' | HashMultimap.create()
        Multisets        | 'unmodifiableMultiset' | HashMultiset.create()
        Queues           | 'synchronizedQueue'    | new ArrayDeque()
        Sets             | 'complementOf'         | EnumSet.of(RoundingMode.UNNECESSARY)
        Tables           | 'transpose'            | TreeBasedTable.create()
        Closeables       | 'closeQuietly'         | new StringReader('foo')
        Files            | 'asByteSource'         | new File('.')
        Flushables       | 'flushQuietly'         | new StringWriter()
        Resources        | 'asByteSource'         | new URL('http://www.google.com')
        InetAddresses    | 'toAddrString'         | InetAddress.localHost
        Booleans         | 'countTrue'            | [true, false] as boolean[]
        Bytes            | 'concat'               | [[1] as byte[], [2] as byte[]] as byte[][]
        Chars            | 'max'                  | [1, 2] as char[]
        Doubles          | 'max'                  | [1d, 2d] as double[]
        Floats           | 'max'                  | [1f, 2f] as float[]
        Ints             | 'max'                  | [1, 2] as int[]
        Longs            | 'max'                  | [1l, 2l] as long[]
        Shorts           | 'max'                  | [1, 2] as short[]
        Futures          | 'getUnchecked'         | Futures.immediateFuture('foo')
        Uninterruptibles | 'getUninterruptibly'   | Futures.immediateFuture('foo')
        Arrays           | 'deepToString'         | ['foo', 'bar'] as String[]
        Collections      | 'synchronizedList'     | ['foo']
    }

    @IgnoreIf({ System.properties['java.version'].startsWith '1.6' })
    def '#utilClass.name is available in Groovy since Java 7'(utilClass, method, object) {
        when:
        def utilCall = eval { utilClass."$method"(object) }
        def extendedCall = eval { object."$method"() }

        then:
        utilCall?.class == extendedCall?.class
        utilCall?.toString() == extendedCall?.toString()

        where:
        utilClass                 | method          | object
        java.nio.file.Files       | 'getFileStore'  | new File('.').toPath()
        java.nio.file.FileSystems | 'getFileSystem' | new URI('file://.')
    }

    // TODO Constraints
    // TODO MapConstraints
    // TODO ObjectArrays
    // TODO ByteStreams
    // TODO CharStreams
    // TODO Channels

    @CompileStatic
    def eval(Closure action) {
        try {
            return action()
        } catch (Exception e) {
            return e
        }
    }
}
