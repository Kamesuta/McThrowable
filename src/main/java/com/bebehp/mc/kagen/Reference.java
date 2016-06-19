package com.bebehp.mc.kagen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reference {
	public static final String MODID = "kagen";
	public static final String NAME = "Kagen";
	public static final String VERSION = "${version}";
	public static final String FORGE = "${forgeversion}";
	public static final String MINECRAFT = "${mcversion}";

	public static Logger logger = LogManager.getLogger(Reference.MODID);
}
