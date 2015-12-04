Rails.application.routes.draw do

  root 'home#index'

  get '/state' => 'home#state'

  get '/vote/:answer/*name' => 'home#place_vote'
  get '/vote' => 'home#vote'

  get '/results' => 'prompt#results'
  get '/ask/*answers' => 'prompt#ask'
  get '/close' => 'prompt#close'
  
end
