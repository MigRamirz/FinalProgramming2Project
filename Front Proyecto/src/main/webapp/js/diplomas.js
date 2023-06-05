
$(document).on("click", "#PDF", function() {
	html2canvas($('#premio')[0], {
		onrendered: function(canvas) {
			var data = canvas.toDataURL();
			var docDefinition = {
				content: [{
					image: data,
					width: 500
				}]
			};
			pdfMake.createPdf(docDefinition).download("Premio.pdf");
		}
	});
});