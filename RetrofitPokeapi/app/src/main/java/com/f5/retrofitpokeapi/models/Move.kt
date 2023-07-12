package com.f5.retrofitpokeapi.models

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)