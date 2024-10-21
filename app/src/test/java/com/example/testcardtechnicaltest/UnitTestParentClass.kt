package com.example.testcardtechnicaltest

import io.mockk.MockKAnnotations
import org.junit.Before

open class UnitTestParentClass {

    @Before
    open fun setUp(){
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

}