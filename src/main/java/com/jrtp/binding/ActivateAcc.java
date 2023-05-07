package com.jrtp.binding;

import lombok.Data;

@Data
public class ActivateAcc {
	private String email;
	private String oldPass;
	private String newPass;
	private String conformPass;
}
