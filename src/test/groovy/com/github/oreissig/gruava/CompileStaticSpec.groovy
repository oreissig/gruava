package com.github.oreissig.gruava

import groovy.transform.CompileStatic
import spock.lang.Specification

class CompileStaticSpec extends Specification {

    def 'can compile statically'() {
        given:
        def collection = [123]

        expect:
        StaticTestClass.test(collection)
    }
}

@CompileStatic
class StaticTestClass {
    static boolean test(Collection<?> c) {
        // Iterables#getOnlyElement(Iterable<T>)
        c.onlyElement != null
    }
}
