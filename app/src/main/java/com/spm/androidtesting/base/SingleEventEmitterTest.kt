package com.spm.androidtesting.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import org.junit.Assert.*
import org.junit.Test

class SingleEventEmitterTest {

    private val testLifecycleOwner = TestLifeCycleOwner()

    @Test
    fun testInitialState() {
        assertEquals(0, testLifecycleOwner.lifeCycleObserverCount())
    }

    @Test
    fun testOneObserver() {
        val emitter = SingleEventEmitter<Int>()
        assertEquals(0, testLifecycleOwner.lifeCycleObserverCount())
        emitter.observe(testLifecycleOwner, Observer {})
        assertEquals(1, testLifecycleOwner.lifeCycleObserverCount())
        assertTrue(emitter.hasObservers())
    }

    @Test
    fun testOneObserverWithOnDestroyEvent() {
        testLifecycleOwner.create()
        val emitter = SingleEventEmitter<Int>()
        emitter.observe(testLifecycleOwner, Observer {})
        assertEquals(1, testLifecycleOwner.lifeCycleObserverCount())
        testLifecycleOwner.destroy()
        assertEquals(0, testLifecycleOwner.lifeCycleObserverCount())
        assertFalse(emitter.hasObservers())
        assertEquals(0, emitter.observersCount())
    }

    @Test
    fun testSameObserverRegisteringTwice() {
        val emitter = SingleEventEmitter<Int>()
        assertEquals(0, testLifecycleOwner.lifeCycleObserverCount())
        emitter.observe(testLifecycleOwner, Observer {})
        emitter.observe(testLifecycleOwner, Observer {})
        assertEquals(2, testLifecycleOwner.lifeCycleObserverCount())
        assertTrue(emitter.hasObservers())
        assertEquals(2, emitter.observersCount())
    }

    @Test
    fun testSameObserverRegisteringTwiceWhenLifecycleEmitsOnDestroyEvent() {
        testLifecycleOwner.create() // necessary so that owner's lifecycle is not in INITIALIZED State
        val emitter = SingleEventEmitter<Int>()
        emitter.observe(testLifecycleOwner, Observer {})
        emitter.observe(testLifecycleOwner, Observer {})
        assertEquals(2, testLifecycleOwner.lifeCycleObserverCount())
        testLifecycleOwner.destroy()
        assertEquals(0, testLifecycleOwner.lifeCycleObserverCount())
        assertFalse(emitter.hasObservers())
        assertEquals(0, emitter.observersCount())
    }

    @Test
    fun testTwoObserversWithTwoDifferentLifeCycleOwners() {
        val testLifecycleOwner2 = TestLifeCycleOwner()
        assertEquals(0, testLifecycleOwner.lifeCycleObserverCount())
        assertEquals(0, testLifecycleOwner2.lifeCycleObserverCount())
        val emitter = SingleEventEmitter<Int>()
        emitter.observe(testLifecycleOwner, Observer { })
        emitter.observe(testLifecycleOwner2, Observer { })
        assertEquals(1, testLifecycleOwner.lifeCycleObserverCount())
        assertEquals(1, testLifecycleOwner2.lifeCycleObserverCount())
        assertTrue(emitter.hasObservers())
        assertEquals(2, emitter.observersCount())
    }

    @Test
    fun testTwoObserversWithTwoDifferentLifeCycleOwnersWhenOnDestroyEventFiredOnlyFromOneLifecycleOwner() {
        val testLifecycleOwner2 = TestLifeCycleOwner()
        testLifecycleOwner.create() // necessary so that owner's lifecycle is not in INITIALIZED State
        testLifecycleOwner2.create()// necessary so that owner's lifecycle is not in INITIALIZED State
        val emitter = SingleEventEmitter<Int>()
        emitter.observe(testLifecycleOwner, Observer { })
        emitter.observe(testLifecycleOwner2, Observer { })
        assertEquals(2, emitter.observersCount())
        assertEquals(1, testLifecycleOwner.lifeCycleObserverCount())
        assertEquals(1, testLifecycleOwner2.lifeCycleObserverCount())
        // only one lifecycle owner emits destroy event
        testLifecycleOwner.destroy()
        assertEquals(0, testLifecycleOwner.lifeCycleObserverCount())
        assertEquals(1, testLifecycleOwner2.lifeCycleObserverCount())
        assertTrue(emitter.hasObservers())
        assertEquals(1, emitter.observersCount())
    }

    @Test
    fun testTwoObserversWithTwoDifferentLifeCycleOwnersWhenOnDestroyEventFiredFromBothLifecycleOwner() {
        val testLifecycleOwner2 = TestLifeCycleOwner()
        testLifecycleOwner.create() // necessary so that owner's lifecycle is not in INITIALIZED State
        testLifecycleOwner2.create()// necessary so that owner's lifecycle is not in INITIALIZED State
        val emitter = SingleEventEmitter<Int>()
        emitter.observe(testLifecycleOwner, Observer { })
        emitter.observe(testLifecycleOwner2, Observer { })
        assertEquals(2, emitter.observersCount())
        assertEquals(1, testLifecycleOwner.lifeCycleObserverCount())
        assertEquals(1, testLifecycleOwner2.lifeCycleObserverCount())
        testLifecycleOwner.destroy()
        testLifecycleOwner2.destroy()
        assertEquals(0, testLifecycleOwner.lifeCycleObserverCount())
        assertEquals(0, testLifecycleOwner2.lifeCycleObserverCount())
        assertFalse(emitter.hasObservers())
        assertEquals(0, emitter.observersCount())
    }
}

class TestLifeCycleOwner : LifecycleOwner {
    private val lifecycle = LifecycleRegistry(this)
    override fun getLifecycle(): Lifecycle = lifecycle
    fun lifeCycleObserverCount(): Int = lifecycle.observerCount
    fun create() = lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun destroy() = lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
}