package com.punam.montra.src.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.punam.montra.R
import com.punam.montra.src.presentation.home.HomeViewModel


@Composable
fun HomeAppBar(
    viewModel: HomeViewModel,
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(0.dp, 0.dp, 32.dp, 32.dp))
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)

                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Avatar(avatarUrl = null)
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp),
                            shape = RoundedCornerShape(40.dp)
                        )
                        .padding(start = 8.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.KeyboardArrowDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "January",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                Icon(
                    imageVector = Icons.Rounded.Notifications, contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )

            }

            Text(
                text = stringResource(R.string.account_balance),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "$${state.balance}",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 25.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(percent = 28))
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(all = 16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(

                            modifier = Modifier
                                .clip(RoundedCornerShape(percent = 28))
                                .background(MaterialTheme.colorScheme.onPrimary)
                                .padding(all = 8.dp)

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.income),
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary),
                                modifier = Modifier
                                    .size(32.dp)


                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Text(
                                text = stringResource(R.string.income),
                                color = MaterialTheme.colorScheme.onPrimary
                            )

                            Text(
                                text = "$${state.income}",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }

                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(percent = 28))
                        .background(MaterialTheme.colorScheme.error)
                        .padding(all = 16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(

                            modifier = Modifier
                                .clip(RoundedCornerShape(percent = 28))
                                .background(MaterialTheme.colorScheme.onError)
                                .padding(all = 8.dp)

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.expense),
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.error),
                                modifier = Modifier
                                    .size(32.dp)


                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Text(
                                text = stringResource(R.string.expenses),
                                color = MaterialTheme.colorScheme.onPrimary
                            )

                            Text(
                                text = "$${state.expenses}",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }

        }
    }
}
