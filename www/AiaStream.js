var exec = require('cordova/exec');

exports.coolMethod = function(arg0, success, error) {
    exec(success, error, "AiaStream", "coolMethod", [arg0]);
};


"use strict";
function AiaStream() {
}

AiaStream.prototype.startStream = function (options) {
	options = options || {};
	cordova.exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "startStream", [url, options]);
};

AiaStream.prototype.stopStream = function (options) {
    options = options || {};
    cordova.exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "stopStream", [options]);
};

AiaStream.install = function () {
	if (!window.plugins) {
		window.plugins = {};
	}
	window.plugins.aiaStream = new AiaStream();
	return window.plugins.aiaStream;
};

cordova.addConstructor(AiaStream.install);