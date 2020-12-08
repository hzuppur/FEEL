package com.example.feel.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.feel.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class FeelingDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: FeelingDatabase
    private lateinit var dao: FeelingDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FeelingDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.feelingDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertFeeling() = runBlockingTest {
        val feeling = Feeling(
            0,
            100,
            100,
            listOf(FeelingSpot(100f, 100f)),
            "trigger",
            "Anger",
            "Reaction",
            100
        )
        dao.addFeeling(feeling)

        val allFeelings = dao.readAllData().getOrAwaitValue()

        assertThat(allFeelings.contains(feeling))
    }

    @Test
    fun insertMultipleFeelings() = runBlockingTest {
        val feeling1 = Feeling(
            0,
            100,
            100,
            listOf(FeelingSpot(100f, 100f)),
            "trigger",
            "Anger",
            "Reaction",
            100
        )

        val feeling2 = Feeling(
            1,
            50,
            50,
            listOf(FeelingSpot(100f, 100f)),
            "trigger",
            "Anger",
            "Reaction",
            100
        )

        val feeling3 = Feeling(
            2,
            10,
            10,
            listOf(FeelingSpot(100f, 100f)),
            "trigger",
            "Anger",
            "Reaction",
            100
        )
        dao.addFeeling(feeling1)
        dao.addFeeling(feeling2)
        dao.addFeeling(feeling3)

        val allFeelings = dao.readAllData().getOrAwaitValue()

        assertThat(allFeelings.contains(feeling1))
        assertThat(allFeelings.contains(feeling2))
        assertThat(allFeelings.contains(feeling3))
    }
}