package com.suikaf1.android.fillintheblank.data

import com.suikaf1.android.fillintheblank.R

val wordList: List<Word> =
    listOf(
        Word(R.string.umbrella_jp, R.string.umbrella),
        Word(R.string.wallet_jp, R.string.wallet),
        Word(R.string.car_jp, R.string.car),
        Word(R.string.train_jp, R.string.train),
        Word(R.string.telephone_jp, R.string.telephone),
        Word(R.string.cell_phone_jp, R.string.cell_phone),
        Word(R.string.tennis_jp, R.string.tennis),
        Word(R.string.mother_jp, R.string.mother),
        Word(R.string.older_sister_jp, R.string.older_sister),
        Word(R.string.father_jp, R.string.father)
    ).shuffled().asSequence().take(10).toList()
