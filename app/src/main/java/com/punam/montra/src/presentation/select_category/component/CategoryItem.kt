package com.punam.montra.src.presentation.select_category.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.punam.montra.src.domain.model.response.CategoryResponse
import com.punam.montra.src.presentation.component.CachedImage
import com.punam.montra.util.CategoryType
import com.punam.montra.util.toColor

@Composable
fun CategoryItem(
    item: CategoryResponse,
    selected: Boolean,
    onChange: (Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp))
            .padding(16.dp)
            .clickable { onChange.invoke(!selected) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(9.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CachedImage(
                url = item.icon,
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(item.color.toColor()),
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        item.color
                            .toColor()
                            .copy(alpha = 0.1f)
                    )
                    .padding(15.dp),
            )
            Text(
                modifier = Modifier.weight(1f),
                text = item.category, style = MaterialTheme.typography.titleMedium,
                color = when (item.type) {
                    CategoryType.Expenses -> MaterialTheme.colorScheme.error
                    CategoryType.Income -> MaterialTheme.colorScheme.primary
                    CategoryType.Transfer -> MaterialTheme.colorScheme.onSurface
                }
            )
            Checkbox(checked = selected, onCheckedChange = onChange)
        }
    }
} 