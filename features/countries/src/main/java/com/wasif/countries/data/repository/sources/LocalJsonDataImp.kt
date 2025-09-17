package com.wasif.countries.data.repository.sources

import com.wasif.countries.data.models.Country

class LocalJsonDataImp : DataSource {
    override suspend fun getCountries(): List<Country> {
        return listOf(
            Country("United States", "US"),
            Country("Canada", "CA"),
            Country("United Kingdom", "UK"),
            Country("Australia", "AU"),
            Country("Germany", "DE"),
            Country("Pakistan", "PK")
        )
    }

    override fun priority(): Int {
        return 1
    }
}