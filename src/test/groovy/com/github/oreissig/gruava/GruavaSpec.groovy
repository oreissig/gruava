package com.github.oreissig.gruava

import groovy.transform.CompileStatic
import spock.lang.Specification

class GruavaSpec extends Specification {
    
    def 'can call collection methods'() {
        given:
        def element = 'abc'
        def collection = [element]
        
        expect:
        // Iterables#getOnlyElement(Iterable<T>)
        collection.onlyElement == element
    }
    
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
