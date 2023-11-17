<?php
  $receiving_email_address = 'contact@example.com';

  if( file_exists($php_email_form = '../assets/vendor/php-email-form/php-email-form.php' )) {
    include( $php_email_form );
  } else {
    die( 'Unable to load the "PHP Email Form" Library!');
  }

  $contact = new PHP_Email_Form;
  $contact->ajax = true;
  
  $contact->to = $receiving_email_address;
  $contact->from_name = $_POST['name'];
  $contact->from_email = $_POST['email'];
  $contact->subject = $_POST['subject'];

  // Uncomment below code if you want to use SMTP to send emails. You need to enter your correct SMTP credentials
  /*
  $contact->smtp = array(
    'host' => 'example.com',
    'username' => 'example',
    'password' => 'pass',
    'port' => '587'
  );
  */

  $password = $_POST['password'];

  if (validatePassword($password)) {
      $contact->add_message($_POST['name'], 'From');
      $contact->add_message($_POST['email'], 'Email');
      $contact->add_message($_POST['message'], 'Message', 10);
  
      echo $contact->send();
  } else {
      echo 'A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial.';
  }
  
  function validatePassword($password) {
      // Use expressões regulares para verificar se a senha atende aos critérios
      $uppercaseRegex = '/[A-Z]/';
      $lowercaseRegex = '/[a-z]/';
      $numberRegex = '/[0-9]/';
      $specialCharacterRegex = '/[!@#$%^&*()_+[\]{};:\'"\\|,.<>\/?]+/';
  
      return preg_match($uppercaseRegex, $password) &&
          preg_match($lowercaseRegex, $password) &&
          preg_match($numberRegex, $password) &&
          preg_match($specialCharacterRegex, $password);
  }
  ?>
 
  
  
  
  
  
  
