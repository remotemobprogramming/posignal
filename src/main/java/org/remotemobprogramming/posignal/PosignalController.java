package org.remotemobprogramming.posignal;

import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posignal")
public class PosignalController {

  private final RocketChatPosignal rocketChatPosignal;
  private final PosignalConfiguration posignalConfiguration;

  public PosignalController(
      RocketChatPosignal rocketChatPosignal, PosignalConfiguration posignalConfiguration) {
    this.rocketChatPosignal = rocketChatPosignal;
    this.posignalConfiguration = posignalConfiguration;
  }

  @GetMapping
  public String index(Model model) {
    System.out.println(posignalConfiguration);
    model.addAttribute("room", posignalConfiguration.zoomPersonalRoomName());
    return "index";
  }

  @GetMapping("/{room}")
  public String signal(@PathVariable("room") String room) throws URISyntaxException, IOException, InterruptedException {
    rocketChatPosignal.posignal(posignalConfiguration);

    return "redirect:/posignal";
  }
}
