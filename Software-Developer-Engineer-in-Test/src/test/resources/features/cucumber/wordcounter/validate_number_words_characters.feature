@word_counter
Feature: Word Counter Test Suite - Validation of Word and Character Counts

  Scenario Outline: Adding Text and Verifying Word Counts, characters and Repeated Words
    Given that andres open the browser of word counter
    When andres fills the text field "<text>"
    And andres validate number of words and characters
    Then andres validate the 3 most repeated words with the number of repetitions


    Examples:
      | text                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
      | Lorem Ipsum is simply dummy dummy text of the printing and Lorem typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. |

