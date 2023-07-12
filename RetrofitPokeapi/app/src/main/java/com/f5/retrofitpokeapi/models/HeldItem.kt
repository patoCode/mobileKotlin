package com.f5.retrofitpokeapi.models

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)