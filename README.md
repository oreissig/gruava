# Gruava

[![Build Status](https://travis-ci.org/oreissig/gruava.svg?branch=master)](https://travis-ci.org/oreissig/gruava)

Groovy already supplements the standard Java library with lots of neat functions, but [Google Guava](https://github.com/google/guava) still has some additional tricks up its sleeve. Let's make use of those in a groovy way!

Gruava makes Guava's helper classes available as Groovy extension modules.
This means you can now call a method like

    import com.google.common.collect.Iterables;
    [...]
    Iterables.getOnlyElement(Iterable<T> iterable);

just like it was part of the standard library:

    myIterable.getOnlyElement();
    // or the Groovy way:
    myIterable.onlyElement

### Usage

1. Add Gruava.
    - Add gruava as compile dependency to your project.
    - It will also bring in guava as a transitive dependency. However, you can choose to use a different version of guava if you like.
2. Use Gruava.
    - The Groovy compiler and runtime will automatically pick up Gruava, you can use it without further ado.
    - IntelliJ IDEA will also pick it up automatically and provide IDE support for the additional methods.
    - Eclipse Groovy integration seems to have problems handling extension modules, at least for me.

### Functionality

You can see exactly which utility classes participate in the Gruava extension module by [inspecting its descriptor](https://github.com/oreissig/gruava/blob/master/src/main/resources/META-INF/services/org.codehaus.groovy.runtime.ExtensionModule).

Be sure to check out Guava's own documentation for more information about the offered functionality, e. g. [Collection Utilities Explained](https://github.com/google/guava/wiki/CollectionUtilitiesExplained)
