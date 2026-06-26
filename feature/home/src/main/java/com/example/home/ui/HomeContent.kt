package com.example.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.domain.model.Repo

@Composable
fun HomeContent(
    repos: List<Repo>,
    modifier: Modifier = Modifier,
    onRepoClick: (Int, String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(count = repos.size) {
            val repo = repos[it]
            RepoItemCard(
                name = repo.name,
                description = repo.description,
                lang = repo.lang.name,
                langColor = Color(repo.lang.color),
                isLastItem = it == repos.lastIndex,
                modifier = modifier.clickable {
                    onRepoClick(repo.id, repo.name)
                }
            )
        }
    }
}