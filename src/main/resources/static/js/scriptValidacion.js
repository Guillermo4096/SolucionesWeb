const input_texts = document.querySelectorAll('.input-text')
  
input_texts.forEach(input => {
  input.addEventListener('keydown', e => {
    const regex = /[A-Za-záéíóúñ ]/

    if(!regex.test(e.key)) e.preventDefault()
    
  })
})

// DNI 
const registrar_dni = document.getElementById('registrar_dni')

// Aceptar solo números
registrar_dni.addEventListener('keydown', e => {
  
  if(["e", "E", "+", "-", "."].includes('5')){
    e.preventDefault()
  }
  
})

// Longitud máxima de 8
registrar_dni.addEventListener('input', e => {
  const value = e.target.value

  if(value.length === 9){
    e.target.value = value.slice(0, -1)
  }
})

// Formulario
const form = document.querySelector('form')

form.addEventListener('submit', e => {

  if(registrar_dni.value.length !== 8) {
    alert('El DNI debe contener 8 caracteres')
    e.preventDefault()
  }
})