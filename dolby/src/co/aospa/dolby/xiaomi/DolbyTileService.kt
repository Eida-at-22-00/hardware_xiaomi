/*
 * Copyright (C) 2023-24 Paranoid Android
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package co.aospa.dolby.xiaomi

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

private const val TAG = "DolbyTileService"

class DolbyTileService : TileService() {

    private val dolbyController by lazy { DolbyController.getInstance(applicationContext) }

    override fun onStartListening() {
        updateTileState()
        super.onStartListening()
    }

    override fun onClick() {
        val isDsOn = dolbyController.dsOn
        val newState = !isDsOn
        
        // Show intermediate state while applying
        qsTile.apply {
            state = Tile.STATE_UNAVAILABLE
            subtitle = "Applying..."
            updateTile()
        }
        
        try {
            dolbyController.setDsOnAndPersist(newState)
            
            // Add delay to allow effect to apply, then update tile
            Handler(Looper.getMainLooper()).postDelayed({
                updateTileState()
            }, 200)
            
        } catch (e: Exception) {
            Log.e(TAG, "Failed to toggle Dolby: $e")
            updateTileState()
        }
        
        super.onClick()
    }

    private fun updateTileState() {
        qsTile.apply {
            val actualState = dolbyController.dsOn
            state = if (actualState) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
            subtitle = dolbyController.getProfileName() ?: getString(R.string.dolby_unknown)
            updateTile()
        }
    }
}
