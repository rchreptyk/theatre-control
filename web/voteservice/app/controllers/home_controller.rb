
class HomeController < ApplicationController
    def index
        render :login
    end

    def vote
        @name = params['name']
    end

    def place_vote
        db = PromptDatabase.instance
        db.register_answer(session.id, params[:name], params[:answer])

        render json: { status: 'answered', results: db.answers }
    end

    def state
        db = PromptDatabase.instance
        question = db.current_question

        if question.nil?
            render json: { status: 'idle' }
        else
            if db.has_answered session.id
                render json: { status: 'answered', results: db.answers }
            else
                render json: { status: 'question', answers: question.answers }
            end    
        end
    end
end
