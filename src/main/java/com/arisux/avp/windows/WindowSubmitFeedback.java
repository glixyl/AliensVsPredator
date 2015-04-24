package com.arisux.avp.windows;

import com.arisux.airi.AIRI;
import com.arisux.airi.api.window.IWindow;
import com.arisux.airi.api.window.gui.windows.Window;
import com.arisux.airi.lib.AccessWrapper;
import com.arisux.airi.lib.GuiElements.GuiCustomButton;
import com.arisux.airi.lib.GuiElements.GuiCustomTextbox;
import com.arisux.airi.lib.ModUtil;
import com.arisux.airi.lib.NetworkUtil;
import com.arisux.airi.lib.WorldUtil.Entities.Players;
import com.arisux.airi.lib.interfaces.IActionPerformed;
import com.arisux.avp.AliensVsPredator;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;

import java.net.URLEncoder;

public class WindowSubmitFeedback extends Window implements IWindow
{
	private GuiCustomButton buttonSubmit;
	public GuiCustomTextbox textbox;
	public String feedback;
	public boolean submitted = false, validated = false;

	public WindowSubmitFeedback()
	{
		super("BETA_PROGRAM_AVP", I18n.format("gui.avp.beta.feedback.info.submit.title"), 100, 100, 110, 40);
		this.buttonSubmit = new GuiCustomButton(0, xPos + 100, yPos + 100, 180, 20, I18n.format("gui.avp.beta.feedback.info.submit.button"), null);
		this.textbox = new GuiCustomTextbox(0, 0, 0, 0);
		this.width = 300;
		this.height = 80;
		this.setWindowCentered(true);
	}

	@Override
	public void onActivated()
	{
		;
	}

	@Override
	public void draw(int mouseX, int mouseY)
	{
		super.draw(mouseX, mouseY);

		this.lineSpacing = 10;

		buttonSubmit.xPosition = xPos;
		buttonSubmit.yPosition = yPos + this.height - buttonSubmit.height;
		buttonSubmit.width = this.width;
		buttonSubmit.baseColor = this.manager.getWindowAPI().getCurrentTheme().getButtonColor();

		textbox.xPosition = xPos + 6;
		textbox.yPosition = yPos + 32;
		textbox.height = 20;
		textbox.width = this.width - 12;
		textbox.setMaxStringLength(200);
		int charsLeft = (textbox.getMaxStringLength() - textbox.getText().length());

		if (!submitted)
		{
			buttonSubmit.drawButton();
			buttonSubmit.setAction(new IActionPerformed()
			{
				@Override
				public void actionPerformed(GuiCustomButton button)
				{
					try
					{
						if (isValidatedBetaTeseterUUID(AccessWrapper.getSession().getPlayerID()) || ModUtil.isDevEnvironment())
						{
							if (!textbox.getText().equals("") && textbox.getText().length() > 12)
							{
								String request = String.format(AliensVsPredator.settings().getUrlFeedbackSubmit(), AccessWrapper.getSession().getUsername(), AccessWrapper.getSession().getPlayerID(), URLEncoder.encode(textbox.getText(), "UTF-8"));
								feedback = NetworkUtil.getURLContents(request);
								AIRI.logger.info("Submitted feedback: %s", feedback);
							}

							validated = true;
						}
						else
						{
							feedback = "INVALID";
							validated = false;
						}
						submitted = true;
					}
					catch (Exception e)
					{
						AIRI.logger.info(e.toString());
					}
				}
			});

			textbox.drawTextBox();
			this.setTitle(I18n.format("gui.avp.beta.feedback.charsleft.title", charsLeft), false);
			this.setDefaultText("gui.avp.beta.feedback.info", true);
		}
		else
		{
			buttonSubmit.setAction(null);

			if (feedback != null)
			{
				if (validated)
				{
					String[] stringReturn = feedback.split(":feedback_split:");

					setTitle("gui.avp.beta.feedback.info.submit.thanks.title", true);
					setDefaultText(I18n.format("gui.avp.beta.feedback.info.submit.thanks", stringReturn[0], stringReturn[2]));
				}
				else
				{
					setTitle("gui.avp.beta.feedback.info.invalidbetatester.title", true);
					setDefaultText("gui.avp.beta.feedback.info.invalidbetatester", true);
				}
			}
			else if (textbox.getText().equals("") && textbox.getText().length() < 12)
			{
				setTitle("gui.avp.beta.feedback.info.spamprevention.title", true);
				setDefaultText("gui.avp.beta.feedback.info.spamprevention", true);
			}
			else
			{
				setTitle("gui.avp.beta.feedback.info.submit.error.title", true);
				setDefaultText("gui.avp.beta.feedback.info.submit.error", true);
			}
		}
	}

	public static boolean isValidatedBetaTeseterUsername(String username)
	{
		return isValidatedBetaTeseterUUID(Players.getUUID(username));
	}

	public static boolean isValidatedBetaTeseterUUID(String uuid)
	{
		return Boolean.parseBoolean(NetworkUtil.getURLContents(String.format(AliensVsPredator.settings().getUrlFeedbackValidation(), uuid)));
	}

	@Override
	public void close()
	{
		super.close();
		this.feedback = null;
		this.submitted = false;
	}

	@Override
	public void onButtonPress(GuiButton button)
	{
		;
	}

	@Override
	public void keyTyped(char c, int id)
	{
		textbox.textboxKeyTyped(c, id);
	}
}
