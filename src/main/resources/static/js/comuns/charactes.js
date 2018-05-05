function createNewChar(json){
	var charDiv = createCharDiv(json.side);
	charDiv.appendChild(createImage(json));
	charDiv.appendChild(createCharName(json.name,json.href));	
	return createParentDiv(charDiv);	
	
	function createCharName(name,href){
		var iDiv = document.createElement('div');
		iDiv.className = 'collection-char-name';
		
		var aClazz = document.createElement('a');
		aClazz.className = 'collection-char-name-link';
		aClazz.rel = 'nofollow';
		aClazz.text = name;
		
		if(href){
			aClazz.href = href;
		}
			
		iDiv.appendChild(aClazz);	
		return iDiv;		
	}
	
	function createImage(json){
		var iDiv = document.createElement('div');
		iDiv.className = 'char-portrait';
		
		var aClazz = document.createElement('a');
		aClazz.className = 'char-portrait-link';
		if(json.href){
			aClazz.href = json.href;
		}
		
		var innerDiv= document.createElement('div');
		innerDiv.className = 'char-portrait-image';
		
		var img= document.createElement('img');
		img.className = 'char-portrait-img';
		img.src=json.image;
		img.alt=json.name;
				
		innerDiv.appendChild(img);
		aClazz.appendChild(innerDiv);
		iDiv.appendChild(aClazz);
		return iDiv;
	}	
	
	function createCharDiv(side){
		var iDiv = document.createElement('div');
		iDiv.className = 'collection-char ';
		return iDiv;
	}
	
	
	function createParentDiv(carDiv){
		var iDiv = document.createElement('div');
		iDiv.className = 'col-xs-6 col-sm-3 col-md-3 col-lg-2';
		iDiv.appendChild(carDiv);		
		return iDiv;
	}
}

function createChar(json) {
	
	var charDiv = createCharDiv(json.side,json.missing);
	var innerDiv;
	
	if(json.missing){
		innerDiv = createMissing(json);
	}else{
		innerDiv = createFull(json);
	}
	var divName = createCharName(json.name,json.href);
	
	
	charDiv.appendChild(innerDiv);
	charDiv.appendChild(divName);	
	return createParentDiv(charDiv);
	
	function createParentDiv(carDiv){
		var iDiv = document.createElement('div');
		iDiv.className = 'col-xs-6 col-sm-3 col-md-3 col-lg-2';
		iDiv.appendChild(carDiv);		
		return iDiv;
	}
	
	function createCharDiv(side,missing){
		var iDiv = document.createElement('div');
		iDiv.className = 'collection-char ' + 'collection-char-'+side+'-side' + (missing? ' collection-char-missing' : '');
		return iDiv;
	}
	
	function createCharName(name,href){
		var iDiv = document.createElement('div');
		iDiv.className = 'collection-char-name';
		
		var aClazz = document.createElement('a');
		aClazz.className = 'collection-char-name-link';
		aClazz.rel = 'nofollow';
		aClazz.text = name;
		
		if(href){
			aClazz.href = href;
		}
			
		iDiv.appendChild(aClazz);	
		return iDiv;		
	}
	
	function createMissing(json){
		var iDiv = document.createElement('div');
		iDiv.className = 'char-portrait char-portrait-' + json.side +  '-side';
		
		var aClazz = document.createElement('a');
		aClazz.className = 'char-portrait-link';
		if(json.href){
			aClazz.href = json.href;
		}
		
		var innerDiv= document.createElement('div');
		innerDiv.className = 'char-portrait-image';
		
		var img= document.createElement('img');
		img.className = 'char-portrait-img';
		img.src=json.charImage;
		img.alt=json.name;
				
		innerDiv.appendChild(img);
		aClazz.appendChild(innerDiv);
		iDiv.appendChild(aClazz);
		return iDiv;
	}
	
	function createFull(json){
		var iDiv = document.createElement('div');
		
		var img = createImageDiv(json);
		var progressBar = createPercent(json.power ,json.maxPower);
		
		iDiv.appendChild(img);
		iDiv.appendChild(progressBar);
		return iDiv;
	
		
		function createImageDiv(json){
			var iDiv = document.createElement('div');
			iDiv.className = 'player-char-portrait char-portrait-full char-portrait-full-gear-t'+json.gear;
				
			var aElement = document.createElement('a');
				if(json.href){ aElement.href = json.href;	}
				aElement.className ="char-portrait-full-link";
				aElement.rel="nofollow";
			iDiv.appendChild(aElement);
			
			var innerDiv= document.createElement('div');
			innerDiv.className = 'char-portrait-image';
			aElement.appendChild(innerDiv);
			
			var img= document.createElement('img');
			img.className = 'char-portrait-img';
			img.src=json.charImage;
			img.alt=json.name;			
			innerDiv.appendChild(img);
			
			for(var x = 1; x<=7;x++){
				var divStar = document.createElement('div');
				divStar.className = ('star star'+ x )+ (json.star < x ? ' star-inactive' : '')  ;
				aElement.appendChild(divStar);
			}
			
			
			var divGear = document.createElement('div');
			divGear.className = 'char-portrait-full-gear';
			aElement.appendChild(divGear);
			
			var divlevel = document.createElement('div');
			divlevel.className = 'char-portrait-full-level';
			divlevel.textContent = json.level;
			aElement.appendChild(divlevel);
			
			var divGearLevel = document.createElement('div');
			divGearLevel.className = 'char-portrait-full-gear-level';
			divGearLevel.textContent = romanize(json.gear);
			aElement.appendChild(divGearLevel);
			
			
			return iDiv;
			/*
			<div class="player-char-portrait char-portrait-full char-portrait-full-gear-t12">
				<a href="/u/cauearua/collection/24/luke-skywalker-farmboy/" class="char-portrait-full-link" rel="nofollow">
					<div class="star star1"></div>
					<div class="star star2"></div>
					<div class="star star3"></div>
					<div class="star star4"></div>
					<div class="star star5"></div>
					<div class="star star6"></div>
					<div class="star star7"></div>
					<div class="char-portrait-full-level">85</div>
					<div class="char-portrait-full-gear-level">XII</div>
				</a>
			</div>
			  
			  
			*/
		}
		
		function createPercent(current,max){
			var iDiv = document.createElement('div');
			iDiv.className = 'collection-char-gp';
			iDiv.dataToggle="tooltip";
			iDiv.dataContainer="body";
			iDiv.title="";
			iDiv.dataOriginalTitle="Power "+current+" / " + max;
			
			var progress = Math.round((current/max * 100) * 100) / 100;
			
			var iProgressbar = document.createElement('div');
			iProgressbar.className = 'collection-char-gp-progress';
			iDiv.appendChild(iProgressbar);
			
			var progressbar = document.createElement('div');
			progressbar.className = 'collection-char-gp-progress-bar';
			progressbar.style = 'width: '+progress+'%;';
			
			iProgressbar.appendChild(progressbar);

			var label = document.createElement('div');
			label.className = 'collection-char-gp-label';
			iDiv.appendChild(label);
			
			var span1 = document.createElement('span');
			span1.className='collection-char-gp-label-value';
			span1.textContent  = progress;
			label.appendChild(span1);
			
			var span2 = document.createElement('span');
			span2.className='collection-char-gp-label-percent';
			span2.textContent  = '%';
			label.appendChild(span2);
						
			return iDiv;
						
		}
	
	}
}

function romanize(num) {
	var lookup = {M:1000,CM:900,D:500,CD:400,C:100,XC:90,L:50,XL:40,X:10,IX:9,V:5,IV:4,I:1},
		roman = '',
		i;
	for ( i in lookup ) {
		while ( num >= lookup[i] ) {
			roman += i;
			num -= lookup[i];
		}
	}
	return roman;
}


/*
			<div class="player-char-portrait char-portrait-full char-portrait-full-gear-t12">
				<a href="/u/cauearua/collection/24/luke-skywalker-farmboy/" class="char-portrait-full-link" rel="nofollow">
					<img class="char-portrait-full-img initial loaded" data-src="//swgoh.gg/static/img/assets/tex.charui_luke_ep4.png" alt="Luke Skywalker (Farmboy)" height="80" width="80" src="//swgoh.gg/static/img/assets/tex.charui_luke_ep4.png" data-was-processed="true">
					<div class="char-portrait-full-gear"></div>
					<div class="star star1"></div>
					<div class="star star2"></div>
					<div class="star star3"></div>
					<div class="star star4"></div>
					<div class="star star5"></div>
					<div class="star star6"></div>
					<div class="star star7"></div>
					<div class="char-portrait-full-level">85</div>
					<div class="char-portrait-full-gear-level">XII</div>
				</a>
			</div>
		
			<div class="collection-char-gp" data-toggle="tooltip" data-container="body" title="" data-original-title="Power 19,299 / 22,125">
				<div class="collection-char-gp-progress">
				<div class="collection-char-gp-progress-bar" style="width: 87.23%;"></div>
				</div>
				<div class="collection-char-gp-label">
				<span class="collection-char-gp-label-value">
				87
				</span>
				<span class="collection-char-gp-label-percent">%</span>
				</div>
			</div>
		

	

*/