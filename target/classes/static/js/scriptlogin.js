var loginAnimation = anime.timeline({
	//loop: true 
});

if(screen.width<858){
	document.getElementById("contenedorImagen").style.display="none";
	document.getElementById("contenedorLogin").style.width="90vw";
	document.getElementById("inputUserName").style.height="4vh";
	document.getElementById("inputUserName").style.fontSize="3rem";
	document.getElementById("inputUserName").style.color="white";
	document.getElementById("title-form").style.fontSize="6rem";
	document.getElementById("inputPassword").style.height="4vh";
	document.getElementById("inputPassword").style.fontSize="3rem";
	document.getElementById("inputPassword").style.color="white";
	document.getElementById("btnIngresar").style.height="7rem";
	document.getElementById("btnIngresar").style.padding=".8vh 3rem";
	document.getElementById("btnIngresar").style.marginTop="2vh";
	document.getElementById("btnIngresar").style.marginBottom="2vh";
	document.getElementById("btnIngresar").style.fontSize="3rem";
	document.getElementById("btnRegresar").style.fontSize="3rem";
}

loginAnimation.add([
	{ 
		// box growing
		targets: ".login-image_wrapper",
		translateX: ["49.99%", "50%"],	
		translateY: ["50%", 0],	
		scale: [0, 1.21],
		opacity: [0, 1],
		delay: 400,
		duration: 500,
		easing: "easeOutBack",
	},	
	{
		// black box movin
		targets: ".login-image_wrapper",
		translateX: [50, -10.7],
		scale: 1.21,
		//translateX(-10.7%) scale(1.21);
		//scale: [1.3, 1],
		easing: "easeOutSine",
		//easing: "easeOutBack",
		delay: 300,
		duration: 300,
	},
	{
	  //form growing
		targets: ".form-signin",
		scale: [0, "1"],
		opacity: [0, "1"],
		//easing: "easeOutCubic",
		//easing: "easeInCubic",
		easing: "easeInOutBack",
		duration: 650,
		offset: '-=550'
	},
	{
		//letters left position
		targets: ".letter-left",
		translateX: 9,	
		easing: "easeOutExpo",
		duration: 1,
	},
	{
		//letters right position
		targets: ".letter-right",
		translateX: -9,	
		easing: "easeOutExpo",
		duration: 1,
	},
	{
		//letters animation
		targets: ".letter",
		opacity: [0, 1],
		delay: function(el, i, l) {
			return 500 + i * 80;     //global delay + letters delay 
		},
		easing: "easeOutExpo",
		duration: 200,
	},
	{
		// letter "i" animation
		targets: "#I",
		opacity: [0, 1],
		delay: 500,
		translateY: ["-200px", 0],
		//easing: "easeOutBack",
		duration: 500,
	},
	{
		//letters position initial
		targets: ".letter-left, .letter-right",
		translateX: 0,	
		easing: [0.41,-0.72, 0.5, 2.04],
		elasticity: 1000,
		duration: 200,
		offset: '-=700'
	},
	{
		// add delay (when loop: true)
	  targets: ".form-signin",
		opacity: 1,
		duration: 2500,
	} 
]);