class PromptController < ApplicationController
    
    def ask
        db = PromptDatabase.instance
        answers = params[:answers].split('/')
        question = Question.new answers

        db.ask_question question

        render json: {success: 'yes' }
    end

    def results
        db = PromptDatabase.instance
        render json: db.answers
    end

    def close
        PromptDatabase.instance.clear_question
        render json: {success: 'yes' }
    end
end
