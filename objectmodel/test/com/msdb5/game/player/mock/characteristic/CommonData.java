package com.msdb5.game.player.mock.characteristic;

import com.msdb5.game.player.ia.player.*;
import com.msdb5.game.player.mock.MockClassicPlayer;
import com.msdb5.game.player.mock.MockCowardPlayer;
import com.msdb5.game.player.mock.MockUnwaveringPlayer;

/**
 * Created by nikiforos on 10/01/16.
 */
class CommonData {

    final static Class[] PERS_IMPL_CLASSES = {MockClassicPlayer.class, MockCowardPlayer.class, MockUnwaveringPlayer.class, Ambiguo.class, BuonCompagno.class, CampioneDecaduto.class, Dubbioso.class, Rialzatore.class};

}
