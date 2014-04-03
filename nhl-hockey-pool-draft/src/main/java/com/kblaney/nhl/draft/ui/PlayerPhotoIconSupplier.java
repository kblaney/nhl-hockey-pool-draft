package com.kblaney.nhl.draft.ui;

import com.kblaney.nhl.Player;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

final class PlayerPhotoIconSupplier
{
  public Icon getIcon(final Player player)
  {
    final URL url = getClass().getResource("/nhlpa-photos/" +
          player.getFirstName() + "_" + player.getLastName() + ".jpg");
    if (url == null)
    {
      return new ImageIcon(getClass().getResource("/unknown-player-photo.jpg"));
    }
    else
    {
      return new ImageIcon(url);
    }
  }
}
