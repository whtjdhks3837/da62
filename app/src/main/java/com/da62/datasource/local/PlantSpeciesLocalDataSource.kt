package com.da62.datasource.local

interface PlantSpeciesLocalDataSource {

    fun getPlantSpecies(input: String): List<String>
}

class PlantSpeciesLocalDataSourceImpl : PlantSpeciesLocalDataSource {

    private val speciesList = listOf(
        "드라세나 산데리아",
        "무늬산 호수",
        "산세베리아",
        "산세베리아 '골든 하나",
        "산호수",
        "assd",
        "ssa"
    )

    override fun getPlantSpecies(input: String) =
        speciesList
            .filter { input.isNotEmpty() }
            .filter {
                it.contains(input)
            }
}